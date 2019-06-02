package com.uanid.crossconfig.format.convert;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uanid.crossconfig.format.convert.impl.JacksonJsonConverter;
import com.uanid.crossconfig.format.convert.impl.JsonNodeDialect;
import com.uanid.crossconfig.node.ConfigNode;
import com.uanid.crossconfig.node.NodeType;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class JacksonJsonConverterTest {

    private String json1;
    private String json2;
    private String json3;
    private ObjectMapper objectMapper;
    private JacksonJsonConverter converter;

    @Before
    public void before() {
        //language=JSON
        json1 = "{\"aaa\":{\"bb\":\"cc\", \"123\": \"dd\"}}";
        //language=JSON
        json2 = "\"adventure-time-formula\"";
        //language=JSON
        json3 = "[{\"aaa\":{\"bb\":\"cc\", \"123\": \"dd\"}},\"just-value\", [\"just-list\", \"just-list-2\"]]";
        objectMapper = new ObjectMapper();
        Dialect dialect = new JsonNodeDialect();
        converter = new JacksonJsonConverter(dialect);
    }

    @Test
    public void convertTest() throws IOException {
        ConfigNode c1 = convert(json1);
        assertSame(c1.getNodeType(), NodeType.TREE);

        ConfigNode c2 = convert(json2);
        assertSame(c2.getNodeType(), NodeType.VALUE);

        ConfigNode c3 = convert(json3);
        assertSame(c3.getNodeType(), NodeType.LIST);
    }

    private ConfigNode convert(String json) throws IOException {
        JsonNode jsonRootNode = objectMapper.readTree(json);
        return converter.convert(jsonRootNode);
    }
}