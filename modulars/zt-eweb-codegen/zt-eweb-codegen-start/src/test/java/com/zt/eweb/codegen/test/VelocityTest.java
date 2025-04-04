package com.zt.eweb.codegen.test;

import java.util.List;
import lombok.Data;
import org.junit.Test;

/**
 * 模块名 : 文件名 : 创建时间 : 2025/4/2 16:50 实现功能 :
 * <p>
 * 作者 : xiaoss 版本 : V1.0.0-SNAPSHOT
 *
 * @see
 * @since ---------------------------------------------------------------- 修改记录 日 期     	版本     修改人
 * 修改内容 2025/4/2      V1.0.0  xiaoss   创建
 * ----------------------------------------------------------------
 */

public class VelocityTest {
  private static final String projectName = "zt-eweb-rbac";

  private static final String baseDir = "./";

  private static final String artifactId = "zt-eweb-rbac";

  private static final String groupId = "com.zt.eweb";

  private static final String version = "1.0.1";

  @Test
  public void test1(){
    FileNode  root = new FileNode();
    root.setId("0");
    root.setName("zt-eweb-rbac");
    root.setSuffix("dir");
    FileNode rootPom = new FileNode();
    rootPom.setId("1");
    rootPom.setName("pom");
    rootPom.setSuffix(".xml");
    rootPom.setContent("<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n"
        + "  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
        + "  xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n"
        + "  <modelVersion>4.0.0</modelVersion>\n"
        + "  <parent>\n"
        + "    <groupId>com.zt.eweb</groupId>\n"
        + "    <artifactId>modulars</artifactId>\n"
        + "    <version>1.1.0</version>\n"
        + "  </parent>\n"
        + "\n"
        + "  <artifactId>zt-eweb-codegen</artifactId>\n"
        + "  <packaging>pom</packaging>\n"
        + "\n"
        + "  <name>zt-eweb-codegen</name>\n"
        + "  <url>http://maven.apache.org</url>\n"
        + "  <modules>\n"
        + "    <module>zt-eweb-codegen-infra</module>\n"
        + "    <module>zt-eweb-codegen-api</module>\n"
        + "    <module>zt-eweb-codegen-app</module>\n"
        + "    <module>zt-eweb-codegen-client</module>\n"
        + "    <module>zt-eweb-codegen-domain</module>\n"
        + "    <module>zt-eweb-codegen-start</module>\n"
        + "    <module>zt-eweb-codegen-common</module>\n"
        + "\n"
        + "  </modules>\n"
        + "\n"
        + "  <properties>\n"
        + "    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>\n"
        + "  </properties>\n"
        + "\n"
        + "  <dependencies>\n"
        + "    <dependency>\n"
        + "      <groupId>junit</groupId>\n"
        + "      <artifactId>junit</artifactId>\n"
        + "      <version>3.8.1</version>\n"
        + "      <scope>test</scope>\n"
        + "    </dependency>\n"
        + "  </dependencies>\n"
        + "</project>\n");
  }

  @Data
  class   FileNode{

    private String  id;
    private String  parentId;
    private String  name;

    private String  suffix;

    private String  content;

    private List<FileNode>  children;
  }
}
