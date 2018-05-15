package demo.Resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;

import javax.annotation.Resources;

import org.jboss.vfs.TempFileProvider;
import org.jboss.vfs.VFS;
import org.jboss.vfs.VirtualFile;
import org.jboss.vfs.spi.RealFileSystem;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.VfsResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class ResourceTest {

	public static void dumpStream(Resource resource) {
        InputStream is = null;
        try {
            is = resource.getInputStream();
            byte[] descBytes = new byte[is.available()]; 
            is.read(descBytes);
//            System.out.println(descBytes.length);
            System.out.println(new String(descBytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                is.close();
            } catch (IOException e) {
            }
        }
    }
	
	public static List<Resource> classPathResourceTest(){
		List<Resource> resourceList= new ArrayList<Resource>();
		
		//if the second parameter is class type(class type path means the path the named class), and the resource path must be the first parameter relative to the second parameter.
		Resource resource = new ClassPathResource("test_alt.properties",ResourceTest.class);
		resourceList.add(resource);
		resource = new ClassPathResource("../../resources/test.properties",ResourceTest.class);
		resourceList.add(resource);
		resource = new ClassPathResource("../../demo/AopAspectJ/test_alt.properties",ResourceTest.class);
		resourceList.add(resource);
		
		//if the second parameter is classLoader type, classLoader path means the path of src(root path).
		resource = new ClassPathResource("demo/Resource/test_alt.properties",ResourceTest.class.getClassLoader());
		resourceList.add(resource);
		resource = new ClassPathResource("Resources/test.properties",ResourceTest.class.getClassLoader());
		resourceList.add(resource);
		resource = new ClassPathResource("demo/AopAspectJ/test_alt.properties",ResourceTest.class.getClassLoader());
		resourceList.add(resource);
		
		//if the second parameter is omitted which means to use the current classLoader path(the path of src) to find the resource.
		resource = new ClassPathResource("demo/Resource/test_alt.properties");
		resourceList.add(resource);
		resource = new ClassPathResource("Resources/test.properties");
		resourceList.add(resource);
		resource = new ClassPathResource("demo/AopAspectJ/test_alt.properties");
		resourceList.add(resource);
		resource = new ClassPathResource("test.properties");
		resourceList.add(resource);
				
		if(resource.exists()) {
        	dumpStream(resource);
        }
		
		return resourceList;
	}
	
	public static void urlResourceTest() throws IOException {
		//Absolute file path
        Resource resource = new UrlResource("file:D:/SourceCode/Java_SourceCode/SpringDemo/test.txt");
        if(resource.exists()) {
            dumpStream(resource);
        }
        System.out.println("this is the urlResourceTest absolute method, path = " + resource.getURL().getPath());

        //Relative file path which is relative to the src folder path of the current project.
        Resource resource1 = new UrlResource("file:demo/AopAspectJ/test_alt.properties");
        if(resource1.exists()) {
            dumpStream(resource1);
        }
        System.out.println("this is the urlResourceTest relative method, path = " + resource1.getURL().getPath());
        
        //URL path
        Resource resource2 = new UrlResource("http://www.sohu.com");
        if(resource2.exists()) {
            dumpStream(resource2);
        }
        System.out.println("this is the urlResourceTest URL method, path = " + resource2.getURL().getPath());
    }

    public static void VfsResourceForRealFileSystemTest() throws IOException {
        VirtualFile virtualFile = VFS.getChild("D:/SourceCode/Java_SourceCode/SpringDemo/test.txt");
        Resource resource = new VfsResource(virtualFile);
        if(resource.exists()) {
            dumpStream(resource);
        }
        System.out.println("this is the VfsResourceForRealFileSystemTest method, path = " + resource.getURL().getPath());     
    }

    public static void VfsResourceForRealFileSystemTest_alt() throws IOException {
        VirtualFile home = VFS.getChild("/home");
        VFS.mount(home, new RealFileSystem(new File("D:/SourceCode/Java_SourceCode/SpringDemo")));
        VirtualFile testFile = home.getChild("test.txt");
        Resource resource = new VfsResource(testFile);
        if(resource.exists()) {
            dumpStream(resource);
        }
        System.out.println("this is the VfsResourceForRealFileSystemTest_alt method, path = " + resource.getFile().getAbsolutePath());     
    }

    public static void VfsResourceForJarTest() throws IOException {
        File realFile = new File("src/test.jar");
        VirtualFile home = VFS.getChild("/home");
        VFS.mountZipExpanded(realFile, home, TempFileProvider.create("tmp", Executors.newScheduledThreadPool(1)));
        VirtualFile testFile = home.getChild("META-INF/spring.handlers");
        Resource resource = new VfsResource(testFile);
        if(resource.exists()) {
            dumpStream(resource);
        }
        System.out.println("this is the VfsResourceForJarTest method, path = " + resource.getFile().getAbsolutePath());       
    }

    public static List<Resource> resourceLoaderTest() {
        ResourceLoader loader = new DefaultResourceLoader();
    	List<Resource> resourceList= new ArrayList<Resource>();
    	
    	//it uses the current classLoader(the path of src) to find the resource
        Resource resource = loader.getResource("classpath:demo/Resource/test_alt.properties");
        resourceList.add(resource);
        //Absolute file path.
        resource = loader.getResource("file:D:/SourceCode/Java_SourceCode/SpringDemo/test.txt");
        resourceList.add(resource);
        //Relative file path means relative to the root path of src.
        resource = loader.getResource("file:demo/Resource/test_alt.properties");
        resourceList.add(resource);
        //if the prefix is not defined, the method will use classpath as the prefix.
        resource = loader.getResource("demo/Resource/test_alt.properties");  
        resourceList.add(resource);
              
        return resourceList;
    }

    public static void resourceInjection() throws IOException{
    	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("resources/resourceInjection.xml");
		ResourceBean resourceBean = ctx.getBean("resourceBean",ResourceBean.class);
		Resource resource = resourceBean.getResource();
		if(resource.exists()) {
            dumpStream(resource);
        }
		
		ResourceBeanMultil resourceBeanMultil = ctx.getBean("resourceBean1",ResourceBeanMultil.class);
		Resource[] resources1 = resourceBeanMultil.getResources();
		System.out.println("this is the resourceInjection method, resources1 length is " + resources1.length);
		for (int i = 0; i< resources1.length; i++)
       		System.out.println("this is the classpathPrefixTest method, resources1 path = " + resources1[i].getURL());  
		
		resourceBeanMultil = ctx.getBean("resourceBean2",ResourceBeanMultil.class);
		Resource[] resources2 = resourceBeanMultil.getResources();
		System.out.println("this is the resourceInjection method, resources2 length is " + resources2.length);
		for (int i = 0; i< resources2.length; i++)
       		System.out.println("this is the classpathPrefixTest method, resources2 path = " + resources2[i].getURL());
		
		resourceBeanMultil = ctx.getBean("resourceBean3",ResourceBeanMultil.class);
		Resource[] resources3 = resourceBeanMultil.getResources();
		System.out.println("this is the resourceInjection method, resources3 length is " + resources3.length);
		for (int i = 0; i< resources3.length; i++)
       		System.out.println("this is the classpathPrefixTest method, resources3 path = " + resources3[i].getURL());
		
		ctx.close();
    }

    public static void classpathPrefixTest() throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        
        //if there is no * in the path expression, only one resource will be returned.
        Resource[] resources1 = resolver.getResources("classpath:META-INF/INDEX.LIST");
        System.out.println("this is the classpathPrefixTest method, resources1 length is " + resources1.length);
        for (int i = 0; i< resources1.length; i++)
       		System.out.println("this is the classpathPrefixTest method, resources1 path = " + resources1[i].getURL());  

        //classpath means only the resources in the the src folder path of the current project will be returned.
        Resource[] resources2 = resolver.getResources("classpath:resources/*.properties");
        System.out.println("this is the classpathPrefixTest method, resources2 length is " + resources2.length);
        for (int i = 0; i< resources2.length; i++)
       		System.out.println("this is the classpathPrefixTest method, resources2 path = " + resources2[i].getURL());  
        
        //classpath* means the resources in the the src folder path of the current project and jars file in bulid-path will be returned. 
        Resource[] resources3 = resolver.getResources("classpath*:META-INF/INDEX.LIST");
        System.out.println("this is the classpathPrefixTest method, resources3 length is " + resources3.length);
        for (int i = 0; i< resources3.length; i++)
        	 System.out.println("this is the classpathPrefixTest method, resources3 path = " + resources3[i].getURL());   
        Resource[] resources4 = resolver.getResources("classpath*:overview*.html");
        System.out.println("this is the classpathPrefixTest method, resources4 length is " + resources4.length);
        for (int i = 0; i< resources4.length; i++)
        	 System.out.println("this is the classpathPrefixTest method, resources4 path = " + resources4[i].getURL()); 
        
        //file prefix in this getResources method must be absolute path of the resource.
        Resource[] resources5 = resolver.getResources("file:/D:/SourceCode/Java_SourceCode/SpringDemo/bin/resources/*.properties");
        System.out.println("this is the classpathPrefixTest method, resources5 length is " + resources5.length);
        for (int i = 0; i< resources5.length; i++)
        	 System.out.println("this is the classpathPrefixTest method, resources5 path = " + resources5[i].getURL()); 
    }
    
	public static void XmlApplicationContextTest() throws IOException {
    	
    	//no prefix relative path means the src folder path of the current project
    	ClassPathXmlApplicationContext ctx1 = new ClassPathXmlApplicationContext("test.xml"); 
    	ResourceBeanMultil resourceBeanMultil = ctx1.getBean("resourceBean1",ResourceBeanMultil.class);
		Resource[] resources = resourceBeanMultil.getResources();
		System.out.println("this is the XmlApplicationContextTest method, resources1 length is " + resources.length);
		for (int i = 0; i< resources.length; i++)
       		System.out.println("this is the XmlApplicationContextTest method, resources1 path = " + resources[i].getURL());  
		ctx1.close();
		
		//classpath relative path means the src folder path of the current project
    	ClassPathXmlApplicationContext ctx3 = new ClassPathXmlApplicationContext("classpath:test.xml"); 
    	resourceBeanMultil = ctx3.getBean("resourceBean1",ResourceBeanMultil.class);
		resources = resourceBeanMultil.getResources();
		System.out.println("this is the XmlApplicationContextTest method, resources3 length is " + resources.length);
		for (int i = 0; i< resources.length; i++)
       		System.out.println("this is the XmlApplicationContextTest method, resources3 path = " + resources[i].getURL());  
		ctx3.close();
		
		//file prefix means the absolute path of the resource
		ClassPathXmlApplicationContext ctx4 = new ClassPathXmlApplicationContext("file:/D:/SourceCode/Java_SourceCode/SpringDemo/bin/test.xml"); 
		resourceBeanMultil = ctx4.getBean("resourceBean1",ResourceBeanMultil.class);
		resources = resourceBeanMultil.getResources();
		System.out.println("this is the XmlApplicationContextTest method, resources4 length is " + resources.length);
		for (int i = 0; i< resources.length; i++)
       		System.out.println("this is the XmlApplicationContextTest method, resources4 path = " + resources[i].getURL());  
		ctx4.close();
		
		//no prefix relative path means the root path of the current project
		FileSystemXmlApplicationContext ctx2 = new FileSystemXmlApplicationContext("src/test.xml"); 
		resourceBeanMultil = ctx2.getBean("resourceBean1",ResourceBeanMultil.class);
		resources = resourceBeanMultil.getResources();
		System.out.println("this is the XmlApplicationContextTest method, resources2 length is " + resources.length);
		for (int i = 0; i< resources.length; i++)
       		System.out.println("this is the XmlApplicationContextTest method, resources2 path = " + resources[i].getURL());  
		ctx2.close();
		
		//classpath relative path means the src folder path of the current project
		FileSystemXmlApplicationContext ctx5 = new FileSystemXmlApplicationContext("classpath:test.xml"); 
		resourceBeanMultil = ctx5.getBean("resourceBean1",ResourceBeanMultil.class);
		resources = resourceBeanMultil.getResources();
		System.out.println("this is the XmlApplicationContextTest method, resources5 length is " + resources.length);
		for (int i = 0; i< resources.length; i++)
       		System.out.println("this is the XmlApplicationContextTest method, resources5 path = " + resources[i].getURL());  
		ctx5.close();
		
		//file prefix means the absolute path of the resource
		ClassPathXmlApplicationContext ctx6 = new ClassPathXmlApplicationContext("file:/D:/SourceCode/Java_SourceCode/SpringDemo/bin/test.xml"); 
		resourceBeanMultil = ctx6.getBean("resourceBean1",ResourceBeanMultil.class);
		resources = resourceBeanMultil.getResources();
		System.out.println("this is the XmlApplicationContextTest method, resources6 length is " + resources.length);
		for (int i = 0; i< resources.length; i++)
       		System.out.println("this is the XmlApplicationContextTest method, resources6 path = " + resources[i].getURL());  
		ctx6.close();
		
		//use BeanFacatory to load beans(depricated)
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(); 
		Resource res = resolver.getResource("classpath:test.xml"); 
		XmlBeanFactory bf = new XmlBeanFactory(res); 
		resourceBeanMultil = bf.getBean("resourceBean1",ResourceBeanMultil.class);
		resources = resourceBeanMultil.getResources();
		System.out.println("this is the XmlApplicationContextTest method, resources7 length is " + resources.length);
		for (int i = 0; i< resources.length; i++)
       		System.out.println("this is the XmlApplicationContextTest method, resources7 path = " + resources[i].getURL());   

    }
    
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
//		urlResourceTest();
		
//		VfsResourceForRealFileSystemTest();
//		VfsResourceForRealFileSystemTest_alt();
//		VfsResourceForJarTest();
		
//		List<Resource> resourceList = classPathResourceTest();
/*		List<Resource> resourceList = resourceLoaderTest();
		int counter = 0;
		for (Iterator<Resource> i = resourceList.iterator(); i.hasNext();){
//			System.out.println("resource["+counter+"] path:" + i.next().getAbsolutePath());
			System.out.println("resource["+counter+"] path:" + i.next().getURI());
			counter++;
		}
		counter = 0;
*/		
//		resourceInjection();	
		
//		classpathPrefixTest();

		XmlApplicationContextTest();
	}
}
