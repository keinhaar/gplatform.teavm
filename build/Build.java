import java.io.File;
import java.io.IOException;

import de.exware.nobuto.java.JavaBuilder;
import de.exware.nobuto.maven.Maven;
import de.exware.nobuto.maven.MavenDependency;
import de.exware.nobuto.utils.Utilities;

public class Build extends JavaBuilder
{
    private static final String PROJECTNAME = "de.exware.gplatform.teavm";
    private static final String TEAVM_VERSION = "0.8.1";
    
    private File jarFile = new File("dist/" + PROJECTNAME + ".jar");
    
    public Build() throws IOException, InterruptedException
    {
        super();
        Maven maven = Maven.getDefaultinstance();
        maven.addRepository("file://" + maven.getLocalRepo() + "/");
    }

    @Override
    public void dist() throws Exception
    {
        super.dist();
        jarFile.getParentFile().mkdir();
        jar(jarFile.getAbsolutePath(), "out", null);
    }
    
    public void installToMaven() throws Exception
    {
        clean();
        dist();
        Maven.getDefaultinstance().installJar(jarFile, "de.exware", PROJECTNAME, getVersion());
    }

    @Override
    public void clean() throws IOException
    {
        Utilities.delete(new File("dist"));
        Utilities.delete(new File("out"));
    }

    @Override
    public void compile() throws Exception
    {      
        addSources("source/java");
        addDependency(new MavenDependency("org.teavm", "teavm-interop", TEAVM_VERSION));
        addDependency(new MavenDependency("org.teavm", "teavm-jso", TEAVM_VERSION));
        addDependency(new MavenDependency("org.teavm", "teavm-jso-apis", TEAVM_VERSION));
        addDependency(new MavenDependency("de.exware", "de.exware.gplatform", "1.0"));
        super.compile();
    }
}
