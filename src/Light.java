public class Light extends SmartDevice{
    private int brightness;

    public Light(String name , String protocol){
        super(name , protocol);
        this.brightness = 50;
    }

    public void setBrightness(int brightness){
        this.brightness = brightness;
    }

    @Override
    public String getInfo(){
        return name + " " + status + " " +  brightness  + "%" + " " + protocol;
    }
}
