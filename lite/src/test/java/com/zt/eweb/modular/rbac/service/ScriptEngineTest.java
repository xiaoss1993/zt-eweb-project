package com.zt.eweb.modular.rbac.service;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;
import org.tinygroup.commons.tools.FileUtil;
import org.tinygroup.tinyscript.*;
import org.tinygroup.tinyscript.impl.DefaultScriptContext;
import org.tinygroup.tinyscript.impl.DefaultScriptEngine;
import org.tinygroup.tinyscript.interpret.ScriptUtil;

import java.io.File;
import java.util.List;

@Slf4j
@SpringBootTest
public class ScriptEngineTest extends TestCase {
    private ScriptEngine scriptEngine;

    protected void setUp() throws Exception {
        scriptEngine = new DefaultScriptEngine();
        scriptEngine.start();
    }

    @Test
    public void testTinyScript() {

    }

    @Test
    public void testLoadScript() throws Exception {
        String text = FileUtil.readFileContent(new File("src/test/resources/class.tsf"), "utf-8");
        ScriptSegment segment = ScriptUtil.getDefault().createScriptSegment(scriptEngine, null, text);
        assertEquals("abc.def", segment.getPackage());

        //判断主键
        assertEquals("abc.def.NewMath", segment.getSegmentId());

        List<String> importList = segment.getImportList();
        assertEquals("org.tinygroup.commons.Math", importList.get(0));
        assertEquals("org.tinygroup.ext.*", importList.get(1));

        ScriptClass scriptClass = segment.getScriptClass();
        assertEquals("NewMath", scriptClass.getClassName());

        ScriptClassField scriptField = scriptClass.getScriptField("num");
        assertEquals("num", scriptField.getFieldName());
        assertEquals(99, scriptField.getValue());

        ScriptClassMethod scriptMethod = scriptClass.getScriptMethod("nomethod");
        assertNull(scriptMethod);

        scriptMethod = scriptClass.getScriptMethod("add2");
        assertNotNull(scriptMethod);
        assertEquals("add2", scriptMethod.getMethodName());

    }

    @Test
    public void testIdentifier() throws Exception {
        ScriptContext context = new DefaultScriptContext();
        context.put("s", "1234567# ");

        //测试tiny脚本的class
        String text = FileUtil.readFileContent(new File("src/test/resources/class.tsf"), "utf-8");
        ScriptSegment segment = ScriptUtil.getDefault().createScriptSegment(scriptEngine, null, text);

        scriptEngine.addScriptSegment(segment);

        Object executed = scriptEngine.execute("math=new abc.def.NewMath(); return math.add(9,2);");

        System.out.println(executed);
    }

}
