package edu.whpu;

import io.agentscope.core.ReActAgent;
import io.agentscope.core.message.Msg;
import io.agentscope.core.model.DashScopeChatModel;
import io.agentscope.core.tool.Tool;
import io.agentscope.core.tool.ToolParam;
import io.agentscope.core.tool.Toolkit;


/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        // 准备工具
        Toolkit toolkit = new Toolkit();
        toolkit.registerTool(new SimpleTools());

        // 创建智能体
        ReActAgent jarvis = ReActAgent.builder()
                .name("Jarvis")
                .sysPrompt("你是一个名为 Jarvis 的编码助手")
                .model(DashScopeChatModel.builder()
                        .apiKey("sk-c795cde315d84c4a885544f8e9f150ff")
                        .modelName("glm-5.1")
                        .build())
                .toolkit(toolkit)
                .build();

        // 发送消息
        Msg msg = Msg.builder()
                .textContent("如何实现滑动窗口算法，有哪些框架用到了滑动窗口算法，请详细说说")
                .build();

        Msg response = jarvis.call(msg).block();
        System.out.println(response.getTextContent());
    }
}

// 工具类
class SimpleTools {
    @Tool(name = "get_time", description = "获取当前时间")
    public String getTime(
            @ToolParam(name = "zone", description = "时区，例如：北京") String zone) {
        return java.time.LocalDateTime.now()
                .format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
