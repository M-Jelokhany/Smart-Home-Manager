public class Thermostat extends SmartDevice{
    private int temperature;

    public Thermostat(String name , String protocol){
        super(name , protocol);
        this.temperature = 20;
    }

    public void setTemperature(int temperature){
        this.temperature = temperature;
    }

    @Override
    public String getInfo(){
        return name + " " + status + " " + temperature + "C" + " " + protocol;
    }


}
