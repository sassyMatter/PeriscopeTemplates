package com.app.services;

import com.app.services.interfaces.CodeComponent;

import java.util.Map;

public class RestComponent implements CodeComponent {
    private final String url;
    private final Map<String, String> headers;
    private final Map<String, String> bodyTypes;
    private final String requestBody;
    private final String requestUrl;
    private final String apiType;
    private final String methodName;

    public RestComponent(String url, Map<String, String> headers, Map<String, String> bodyTypes,
                         String requestBody, String requestUrl, String apiType, String methodName) {
        this.url = url;
        this.headers = headers;
        this.bodyTypes = bodyTypes;
        this.requestBody = requestBody;
        this.requestUrl = requestUrl;
        this.apiType = apiType;
        this.methodName = methodName;
    }

    @Override
    public String generateCode() {
        // Generate code to create a REST API in a Spring Boot application with multiple properties
        StringBuilder codeBuilder = new StringBuilder();


        codeBuilder.append("    @Autowired").append("\n");
        codeBuilder.append("    private MyService myService;").append("\n");
        codeBuilder.append("\n");
        codeBuilder.append("    @").append(apiType).append("(\"").append(requestUrl).append("\")").append("\n");
        codeBuilder.append("    public ResponseEntity<?> ").append(methodName).append("(");

        // Generate code for headers
        if (headers != null && !headers.isEmpty()) {
            codeBuilder.append("@RequestHeader Map<String, String> headers, ");
        }

        // Generate code for request body
        if (requestBody != null && !requestBody.isEmpty()) {
            codeBuilder.append("@RequestBody ").append(bodyTypes.get(requestBody)).append(" request");
        }

        codeBuilder.append(") {").append("\n");
        codeBuilder.append("        // Process the request and perform desired operations").append("\n");
        codeBuilder.append("        // ...").append("\n");
        codeBuilder.append("        myService.").append(methodName).append("(");

        // Generate code for request parameters
        if (headers != null && !headers.isEmpty()) {
            codeBuilder.append("headers, ");
        }

        if (requestBody != null && !requestBody.isEmpty()) {
            codeBuilder.append("request");
        }

        codeBuilder.append(");").append("\n");
        codeBuilder.append("        return ResponseEntity.ok().build();").append("\n");
        codeBuilder.append("    }").append("\n");


        return codeBuilder.toString();
    }
}
