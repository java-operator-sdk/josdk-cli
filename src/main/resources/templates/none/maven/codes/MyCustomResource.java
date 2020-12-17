package {{groupid}};

import io.fabric8.kubernetes.client.CustomResource;

public class MyCustomResource extends CustomResource {

    private MyCustomResourceSpec spec;

    private MyCustomResourceStatus status;

    public MyCustomResourceSpec getSpec() {
        return spec;
    }

    public void setSpec(MyCustomResourceSpec spec) {
        this.spec = spec;
    }

    public MyCustomResourceStatus getStatus() {
        return status;
    }

    public void setStatus(MyCustomResourceStatus status) {
        this.status = status;
    }
}