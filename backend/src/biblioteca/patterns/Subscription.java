package biblioteca.patterns;

// [BRIDGE]
public abstract class Subscription {
    protected AccessMethod accessMethod;

    public Subscription(AccessMethod method) {
        this.accessMethod = method;
    }

    public abstract void accessContent();
}

interface AccessMethod {
    void access();
}

class StreamingAccess implements AccessMethod {
    @Override
    public void access() {
        System.out.println("Accediendo vía streaming");
    }
}

class DownloadAccess implements AccessMethod {
    @Override
    public void access() {
        System.out.println("Accediendo vía descarga");
    }
}

class PremiumSubscription extends Subscription {
    public PremiumSubscription(AccessMethod method) {
        super(method);
    }

    @Override
    public void accessContent() {
        accessMethod.access();
    }
}