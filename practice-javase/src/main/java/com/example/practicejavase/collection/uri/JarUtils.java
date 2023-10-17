package com.example.practicejavase.collection.uri;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yenbay at 2019-07-25 20:41:45 Jar工具，classpath工具
 */
public class JarUtils {

    private static String classpath = null;
    private static final Pattern LAST_SLASH_PATTERN = Pattern.compile("[\\\\/]+$");

    /**
     * 中文格式
     */
    private static final Pattern CHINESE_PATTERN = Pattern.compile("[\\u00e0-\\u01d4]");

    /**
     * 在Jar包内的路径格式，两个分组，第一个是根jar包，后一个是jar包内路径，如果有多层jar包，则后面的jar包路径包含在分组2中
     */
    private static final Pattern INSIDE_A_JAR_PATTERN = Pattern.compile("(?i)^((?:(?!\\.jar).)*\\.jar)(!.*|)$");

    /**
     * 判断是否Windows盘符
     */
    private static final Pattern WINDOWS_DISK_START_PATTERN = Pattern.compile("^\\/?[a-zA-Z]:");

    /**
     * 文件所在目录格式，分组1为目录地址，如果没有目录地址则分组1为null
     */
    private static final Pattern FILE_DIR_PATTERN = Pattern.compile("^(.*[\\\\/])?[^\\\\/]*$");

    /**
     * java自带包格式，用于排除自带包类获取包所在路径
     */
    private static final Pattern JAVA_LANG_PACKAGE_PATTERN = Pattern.compile("^(javax?|sun)\\.");

    /**
     * 去除file: jar:头，用于获取绝对路径
     */
    private static final Pattern JAR_FILE_PATTERN = Pattern.compile("^jar:(file:)?|^file:");

    private static final char[] digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /**
     * 多种方式获取文件流<br>
     * 优先级依次为:<br>
     * 1. 判断JAR包内文件<br>
     * 2. 绝对路径，filePath以磁盘绝对路径方式展现<br>
     * 3. 相对路径，如果给定类在jar包，相对jar包的位置<br>
     * 4. 相对路径，给定类不在jar包内，取所在classpath下的文件
     *
     * @param relativeClass
     * @param filePath
     * @return
     * @throws Exception
     */
    public static InputStream fetchFileVariousWays(Class<?> relativeClass, String filePath) throws Exception {
        if (filePath == null) {
            return null;
        }
        if (filePath.startsWith("jar:file:")) {
            // jar包内
            return getInnerJarFileInputStream(filePath);
        }
        if (filePath.startsWith("file:")) {
            return new FileInputStream(filePath.substring(5));
        }
        if (filePath.startsWith("classpath:")) {
            String rel = filePath.substring(10);
            InputStream is = relativeClass.getResourceAsStream(rel);
            if (is != null) {
                return is;
            }
            if (rel.startsWith("/")) {
                rel = rel.substring(1);
            } else {
                rel = "/" + rel;
            }
            return relativeClass.getResourceAsStream(rel);
        }
        if (filePath.startsWith("/")
                ||
                filePath.startsWith("\\")
                ||
                isWindowsAbsolutePath(filePath)
        ) {
            // 绝对路径
            File file = new File(filePath);
            if (file.exists() && file.isFile()) {
                return new FileInputStream(file);
            }
        }
        // 相对路径
        if (relativeClass == null) {
            throw new NullPointerException("Null relativeClass!");
        }

        if (isInsideJar(relativeClass)) {
            // 先取jar包所在路径的包外文件
            String jarFolder = getClassJarRootFolder(relativeClass);
            File file = new File(jarFolder + File.separator + filePath);
            if (file.exists() && file.isFile()) {
                return new FileInputStream(file);
            }
            // 包外不存在，则找包内文件
            InputStream is = getInnerJarFileInputStream(getClassJarRootPath(relativeClass), filePath);
            if (is != null) {
                return is;
            }
            is = getInnerJarFileInputStream(getClassJarRootPath(relativeClass), fixResources(filePath));
            if (is != null) {
                return is;
            }
        }
        // 不在jar包内相对路径
        try {
            return relativeClass.getClassLoader().getResourceAsStream(filePath);
        } catch (Exception e) {
            return relativeClass.getClassLoader().getResourceAsStream(fixResources(filePath));
        }
    }

    /**
     * 增强兼容性，支持resources目录下资源
     *
     * @param source
     * @return
     */
    private static String fixResources(String source) {
        if (source.startsWith("/resources/")) {
            return source.substring(10);
        }
        if (source.startsWith("resources/")) {
            return source.substring(9);
        }
        if (source.startsWith("/")) {
            return "/resources" + source;
        }
        return "resources/" + source;
    }

    /**
     * 判断是windows下的磁盘绝对路径，示例： D:
     *
     * @param path
     * @return
     */
    public static boolean isWindowsAbsolutePath(String path) {
        return path != null && "\\".equals(File.separator) && WINDOWS_DISK_START_PATTERN.matcher(path).find();
    }

    /**
     * 如果给定类在jar包内，则查找jar包绝对路径，如果给定类不在jar包内，则查找给定类所在的classpath绝对路径
     *
     * @param relativeClass
     * @return
     */
    public static String getClassJarRootPath(Class<?> relativeClass) {
        String codeSourcePosition = getClassCodeSourcePosition(relativeClass);
        Matcher matcher = INSIDE_A_JAR_PATTERN.matcher(codeSourcePosition);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return getClassPath(relativeClass);
    }

    /**
     * 如果给定类在jar包内，则查找jar包所在文件夹绝对路径，如果给定类不在jar包内，则查找给定类所在的classpath绝对路径
     *
     * @param relativeClass class
     * @return
     */
    public static String getClassJarRootFolder(Class<?> relativeClass) {
        String jarPath = getClassJarRootPath(relativeClass);
        if (jarPath.toLowerCase().endsWith(".jar")) {
            return getDirectory(jarPath);
        }
        return jarPath;
    }

    /**
     * 获取类编译二进制文件所在jar包内的路径, 如果在jar包内，则返回jar包绝对路径, 如果不在jar包内，则返回对应.class文件所在classloader根文件夹路径
     *
     * @param clazz
     * @return
     */
    private static String getClassCodeSourcePosition(Class<?> clazz) {
        if (JAVA_LANG_PACKAGE_PATTERN.matcher(clazz.getName()).find()) {
            return ClassLoader.getSystemClassLoader().getResource("").getPath();
        }
        String path = clazz.getProtectionDomain().getCodeSource().getLocation().getPath();
        if (path.startsWith("./")) {
            // 通过自定义ClassLoader加载的相对路径，常见于Eclipse直接导出jar包
            try {
                String trySystemLoadPath = ClassLoader.getSystemClassLoader().getResource(clazz.getName().replace(".", "/") + ".class").getPath();
                // 支持Eclipse直接打包导出的jar获取
                if (trySystemLoadPath != null) {
                    path = trySystemLoadPath;
                }
            } catch (Exception e) {
                path = ClassLoader.getSystemClassLoader().getResource("").getPath();
            }
        }
        if (path.toLowerCase().startsWith("rsrc:")) {
            // 相对路径
            path = ClassLoader.getSystemClassLoader().getResource(path.substring(5).replaceAll("!/", "")).getPath();
        }
        path = JAR_FILE_PATTERN.matcher(path).replaceAll("");
        // 中文编码使用UTF8转码
        if (CHINESE_PATTERN.matcher(path).find()) {
            try {
                return URLDecoder.decode(path, "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return path;
    }

    private static boolean isJarInsidePath(String position) {
        return position != null && INSIDE_A_JAR_PATTERN.matcher(position).find();
    }

    /**
     * 判断在jar包内
     *
     * @param clazz
     * @return
     */
    public static boolean isInsideJar(Class<?> clazz) {
        return isJarInsidePath(getClassCodeSourcePosition(clazz));
    }


    /**
     * 创建由jar包所在绝对路径生成的固定值，当jar包变更位置或者jar包变更名称时，该值随之变更，否则值不变,常用于集群部署确定多个日志或文件存放路径
     *
     * @param clazz
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    public static String createFixedJarPathId(Class<?> clazz) {
        if (clazz == null) {
            throw new NullPointerException();
        }
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update((getClassJarRootPath(clazz) + "!#" + clazz.getName()).getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] b = md.digest();
        String str = "";
        for (int i = 0; i < b.length; i++) {
            char[] ob = new char[2];
            ob[0] = digit[(b[i] >>> 4) & 0X0F];
            ob[1] = digit[b[i] & 0X0F];
            str += new String(ob);
        }
        return str.toLowerCase();
    }

    /**
     * 获取系统桌面地址
     *
     * @return
     */
    public static String getDeskopDir() {
        File homeDir = FileSystemView.getFileSystemView().getHomeDirectory();
        String home = homeDir.getAbsolutePath();
        if (home.endsWith("Desktop") || home.endsWith("desktop")) {
            return home;
        }
        return home + "/Desktop";
    }

    /**
     * 返回classpath路径
     *
     * @return
     */
    public static String getClassPath(Class<?> relativeClass) {
        URL url = relativeClass.getResource("");
        if (url != null) {
            String packageName = relativeClass.getPackageName();
            String relativePath = url.getPath();
            relativePath = relativePath.replace("\\", "/");
            String packagePath = packageName.replace(".", "/");
            if (LAST_SLASH_PATTERN.matcher(relativePath).find()) {
                if (!packagePath.endsWith("/")) {
                    packagePath += "/";
                }
            }
            if (relativePath.endsWith(packagePath)) {
                return relativePath.substring(0, relativePath.length() - packagePath.length());
            }
        }
        // Code should not be here.
        return getDirectory(getClassCodeSourcePosition(relativeClass));
    }

    private static String getDirectory(String filePath) {
        Matcher matcher = FILE_DIR_PATTERN.matcher(filePath);
        if (matcher.find()) {
            String dir = matcher.group(1);
            if (dir == null) {
                throw new IllegalArgumentException(filePath);
            }
            return dir;
        }
        throw new IllegalArgumentException(filePath);
    }

    public static String getClassPath() {
        if (classpath != null) {
            return classpath;
        }
        classpath = getClassPath(JarUtils.class);
        return classpath;
    }

    /**
     * jar包内文件流读取，必须关闭文件流
     *
     * @param jarPath   jar文件位置
     * @param entryName jar文件里面文件的路径，相对jar包根路径
     * @return
     * @throws Exception
     */
    public static InputStream getInnerJarFileInputStream(String jarPath, String entryName) {
        try {
            jarPath = JAR_FILE_PATTERN.matcher(jarPath).replaceAll("").replaceAll("[\\\\\\/]{2,}", "/");
            entryName = entryName.replaceAll("^[\\\\\\/]+", "").replaceAll("[\\\\\\/]{2,}", "/");
            return getInnerJarFileInputStream("jar:file:" + jarPath + "!/" + entryName);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * jar包内文件流读取，必须关闭文件流
     *
     * @param fileInJarPath jar中文件全路径
     * @return
     * @throws Exception
     */
    public static InputStream getInnerJarFileInputStream(String fileInJarPath) {
        try {
            URL url = new URL(fileInJarPath.replace("\\", "/"));
            JarURLConnection jarConnection = (JarURLConnection) url.openConnection();
            return jarConnection.getInputStream();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 遍历 jar包内文件
     *
     * @param jarPath             jar文件路径
     * @param elementReadListener 遍历处理器
     * @throws IOException
     */
    public static void traverseInnerJarFile(String jarPath, ElementReadListener elementReadListener) throws IOException {
        try (JarFile jf = new JarFile(jarPath)) {
            elementReadListener.beforeRead(jf);
            Enumeration<JarEntry> enume = jf.entries();
            long count = 0;
            while (enume.hasMoreElements()) {
                count++;
                elementReadListener.onReadElement(jarPath, count, enume.nextElement());
            }
            elementReadListener.afterRead(jf);
        }
    }

    /**
     * Created by yenbay at 2019-07-26 13:23:58 文件读取监听
     */
    public interface ElementReadListener {

        /**
         * 开始读取之前操作
         *
         * @param jarFile
         */
        public default void beforeRead(JarFile jarFile) {
        }

        ;

        /**
         * 读取行之后操作
         *
         * @param jarPath   jar位置
         * @param fileCount 文件数量
         * @param jarEntry  文件入口
         */
        void onReadElement(String jarPath, long fileCount, JarEntry jarEntry);

        /**
         * 读取完之后的操作
         *
         * @param jarFile
         */
        public default void afterRead(JarFile jarFile) {
        }

        ;
    }
}