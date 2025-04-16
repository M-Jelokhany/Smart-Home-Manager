public abstract class SmartDevice {
    protected String name;
    protected String protocol;
    protected String status;

    public SmartDevice(String name , String protocol){
        this.name = name;
        this.protocol = protocol;
        this.status = "off";
    }

    public String getName(){
        return name;
    }

    public String getProtocol(){
        return protocol;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public abstract String getInfo();
}
