import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

public class Application {
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(11220);

        String webAppDir = "tomcat/src/main/resources/webapp";
        StandardContext ctx = (StandardContext)tomcat.addWebapp("/",
                new File(webAppDir).getAbsolutePath());

        File additionalFilesPath = new File("tomcat/target/classes");
        WebResourceRoot webResourceRoot = new StandardRoot(ctx);
        webResourceRoot.addPreResources(new DirResourceSet(webResourceRoot, "/WEB-INF/classes",
                additionalFilesPath.getAbsolutePath(), "/"));
        ctx.setResources(webResourceRoot);

        tomcat.start();
        tomcat.getServer().await();
    }
}
