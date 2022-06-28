package gud.template.config;

public class EndpointURLs {

    //Root
    public static final String TEMPLATE = "/template";

    //Endpoints
    public static final String TEST_STRING = TEMPLATE + "/test-string";

    public static String getUrls(){
        return String.join("\n",
                TEST_STRING
        );
    }

}
