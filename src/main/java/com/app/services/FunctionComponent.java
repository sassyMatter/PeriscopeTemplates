package com.app.services;

import com.app.services.interfaces.CodeComponent;

import java.util.Map;

public class FunctionComponent implements CodeComponent {
    private final Map<String, String> parameters;
    private final String returnType;
    private final String functionBody;

    // there will be complex method too, where dependencies and import would be required,
    // we will look into it later

    public FunctionComponent(Map<String, String> parameters, String returnType, String functionBody) {
        this.parameters = parameters;
        this.returnType = returnType;
        this.functionBody = functionBody;
    }

    @Override
    public String generateCode() {
        // Generate code for the function
        StringBuilder codeBuilder = new StringBuilder();

        // Append method signature
        codeBuilder.append(returnType).append(" functionName(");
        boolean isFirstParameter = true;
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            if (!isFirstParameter) {
                codeBuilder.append(", ");
            }
            codeBuilder.append(entry.getValue()).append(" ").append(entry.getKey());
            isFirstParameter = false;
        }
        codeBuilder.append(") {").append("\n");

        // Append function body
        codeBuilder.append(functionBody).append("\n");

        // Append return statement if the return type is not void
//        if (!returnType.equals("void")) {
//            codeBuilder.append("    return <returnValue>;").append("\n");
//        }

        codeBuilder.append("}").append("\n");

        return codeBuilder.toString();
    }
}

