package priv.jzy.front.util;

import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SvgUtil {

    private final static Map<String, SVGPath> svgPaths;
    private final static String PREFIX = "/icon";

    static {
        svgPaths = new ConcurrentHashMap<>();
    }

    /**
     * 获取 resources/icon 资源下的 SVG 图片
     * 没有就返回空值
     *
     * @param svgName svg 名称
     * @param color   填充颜色
     */
    public static SVGPath getSVGPathByName(String svgName, String color) {
        if (!RegexUtil.isMatch(RegexUtil.COLOR, color)) throw new RuntimeException(color + "颜色格式错误");
        if (svgPaths.containsKey(svgName)) {
            SVGPath svgPath = svgPaths.get(svgName);
            svgPath.setFill(Paint.valueOf(color));
            return svgPath;
        }
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            //禁止DTD验证,防止网络阻塞
            builder.setEntityResolver((publicId, systemId) -> new InputSource(new StringReader("")));
            InputStream inputStream = SvgUtil.class.getResourceAsStream(PREFIX + "/" + svgName);
//            Assert.notNull(inputStream, svgName + " file not found in /resources/icon Folder.");
            Document d = builder.parse(inputStream);
            NodeList list = d.getElementsByTagName("path");
            // 合并 path
            StringBuilder confluence = new StringBuilder();
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                String content = node.getAttributes().getNamedItem("d").getNodeValue();
                confluence.append(content).append(" ");
            }
            // 创建 SVGPath 对象
            SVGPath path = new SVGPath();
            path.setContent(confluence.toString());
            path.setFill(Paint.valueOf(color));
            path.setScaleX(0.1);
            path.setScaleY(0.1);
            path.maxWidth(1);
            path.maxHeight(1);
            // 推入数组
            svgPaths.put(svgName, path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return svgPaths.get(svgName);
    }
}
