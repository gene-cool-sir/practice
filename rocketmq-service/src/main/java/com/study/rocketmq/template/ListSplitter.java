package com.study.rocketmq.template;

import org.apache.rocketmq.common.message.Message;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author gene 大批量数据分割发送
 */
public class ListSplitter implements Iterator<List<Message>> {
        private static final int SIZE_LIMIT = 1000 * 1000;
        private final List<org.apache.rocketmq.common.message.Message> messages;
        private int currIndex;

        public ListSplitter(List<org.apache.rocketmq.common.message.Message> messages) {
            this.messages = messages;
        }

        @Override
        public boolean hasNext() {
            return currIndex < messages.size();
        }

        @Override
        public List<org.apache.rocketmq.common.message.Message> next() {
            int nextIndex = currIndex;
            int totalSize = 0;
            for (; nextIndex < messages.size(); nextIndex++) {
                org.apache.rocketmq.common.message.Message message = messages.get(nextIndex);
                int tmpSize = message.getTopic().length() + message.getBody().length;
                Map<String, String> properties = message.getProperties();
                for (Map.Entry<String, String> entry : properties.entrySet()) {
                    tmpSize += entry.getKey().length() + entry.getValue().length();
                }
                //for log overhead
                tmpSize = tmpSize + 20;
                if (tmpSize > SIZE_LIMIT) {
                    //it is unexpected that single message exceeds the sizeLimit
                    //here just let it go, otherwise it will block the splitting process
                    if (nextIndex - currIndex == 0) {
                        //if the next sublist has no element, add this one and then break, otherwise just break
                        nextIndex++;
                    }
                    break;
                }
                if (tmpSize + totalSize > SIZE_LIMIT) {
                    break;
                } else {
                    totalSize += tmpSize;
                }

            }
            List<org.apache.rocketmq.common.message.Message> subList = messages.subList(currIndex, nextIndex);
            currIndex = nextIndex;
            return subList;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not allowed to remove");
        }
    }