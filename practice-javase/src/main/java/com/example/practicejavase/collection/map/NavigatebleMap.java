package com.example.practicejavase.collection.map;

import java.util.*;

public class NavigatebleMap {
    // 存储地点的经纬度信息
    private Map<String, Coordinates> locations;
    // 存储地点之间的连接关系和距离信息
    private Map<String, Map<String, Integer>> connections;

    public NavigatebleMap() {
        this.locations = new HashMap<>();
        this.connections = new HashMap<>();
    }

    // 添加一个地点的经纬度信息
    public void addLocation(String location, double latitude, double longitude) {
        locations.put(location, new Coordinates(latitude, longitude));
    }

    // 添加地点之间的连接关系和距离信息
    public void addConnection(String location1, String location2, int distance) {
        if (!connections.containsKey(location1)) {
            connections.put(location1, new HashMap<>());
        }
        connections.get(location1).put(location2, distance);

        if (!connections.containsKey(location2)) {
            connections.put(location2, new HashMap<>());
        }
        connections.get(location2).put(location1, distance);
    }

    // 计算从起始位置到目标位置的最短路径
    public List<String> findShortestPath(String startLocation, String targetLocation) {
        // 使用 Dijkstra 算法计算最短路径
        PriorityQueue<Node> queue = new PriorityQueue<>();
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();

        // 初始化距离信息
        for (String location : locations.keySet()) {
            if (location.equals(startLocation)) {
                distances.put(location, 0);
                queue.offer(new Node(location, 0));
            } else {
                distances.put(location, Integer.MAX_VALUE);
            }
            previous.put(location, null);
        }

        // 开始搜索最短路径
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            String currentLocation = currentNode.location;
            int currentDistance = currentNode.distance;

            // 如果当前位置已经是目标位置，则终止搜索
            if (currentLocation.equals(targetLocation)) {
                break;
            }

            // 遍历当前位置的所有相邻位置
            for (String neighbor : connections.get(currentLocation).keySet()) {
                int distance = connections.get(currentLocation).get(neighbor);
                int newDistance = currentDistance + distance;

                if (newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    previous.put(neighbor, currentLocation);
                    queue.offer(new Node(neighbor, newDistance));
                }
            }
        }

        // 根据 previous 表构建最短路径
        List<String> shortestPath = new ArrayList<>();
        String currentLocation = targetLocation;
        while (currentLocation != null) {
            shortestPath.add(0, currentLocation);
            currentLocation = previous.get(currentLocation);
        }

        return shortestPath;
    }

    // 根据关键词进行地点搜索
    public List<String> searchPlaces(String keyword) {
        List<String> places = new ArrayList<>();

        for (String location : locations.keySet()) {
            if (location.toLowerCase().contains(keyword.toLowerCase())) {
                places.add(location);
            }
        }

        return places;
    }

    // 测试代码
    public static void main(String[] args) {
        NavigatebleMap map = new NavigatebleMap();
        map.addLocation("A", 40.7128, 74.0060);
        map.addLocation("B", 34.0522, 118.2437);
        map.addLocation("C", 51.5074, 0.1278);

        map.addConnection("A", "B", 10);
        map.addConnection("A", "C", 15);
        map.addConnection("B", "C", 20);

        List<String> shortestPath = map.findShortestPath("A", "C");
        System.out.println("Shortest path: " + shortestPath);

        List<String> places = map.searchPlaces("A");
        System.out.println("Search results: " + places);
    }

    // 内部类，用于存储位置的经纬度信息
    private static class Coordinates {
        private double latitude;
        private double longitude;

        public Coordinates(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }
    }

    // 内部类，用于辅助最短路径计算
    private static class Node implements Comparable<Node> {
        private String location;
        private int distance;

        public Node(String location, int distance) {
            this.location = location;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }
}
