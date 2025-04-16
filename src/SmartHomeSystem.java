import java.util.ArrayList;
import java.util.List;

public class SmartHomeSystem {
    private List<SmartDevice> devices;
    private List<Rule> rules;

    public SmartHomeSystem() {
        devices = new ArrayList<>();
        rules = new ArrayList<>();
    }

    public void addDevice(String type, String name, String protocol)throws Exception {
        if ((!type.equals("light") && !type.equals("thermostat")) || (!protocol.equals("WiFi") && !protocol.equals("Bluetooth"))) {
            throw new Exception("invalid input");
        }

        for (SmartDevice device : devices) {
            if (device.getName().equals(name)) {
                throw new Exception("duplicate device name");
            }
        }

        SmartDevice newDevice;
        if (type.equals("light")) {
            newDevice = new Light(name, protocol);
        }
        else {
            newDevice = new Thermostat(name, protocol);
        }

        devices.add(newDevice);
        System.out.println("device added successfully");
    }

    public void setDevice(String name, String property, String value)throws Exception {
        boolean notFound = true;
        for (SmartDevice device : devices) {
            if (device.getName().equals(name)) {
                notFound = false;
            }
        }
        if (notFound) {
            throw new Exception("device not found");
        }

        for (SmartDevice device : devices) {
            if (device.getName().equals(name)) {
                if (device instanceof Light) {
                    if (!property.equals("status") && !property.equals("brightness")) {
                        throw new Exception("invalid property");
                    }
                }
                if (device instanceof Thermostat) {
                    if (!property.equals("status") && !property.equals("temperature")) {
                        throw new Exception("invalid property");
                    }
                }
            }
        }

        for (SmartDevice device : devices) {
            if (device.getName().equals(name)) {
                if (property.equals("status")) {
                    if (value.equals("on") || value.equals("off")) {
                        device.setStatus(value);
                        System.out.println("device updated successfully");
                    }
                    else {
                        throw new Exception("invalid value");
                    }
                }

                if (property.equals("brightness")) {
                    try {
                        int brightness = Integer.parseInt(value);
                        if (brightness < 0 || brightness > 100) {
                            throw new Exception("invalid value");
                        }
                        ((Light) device).setBrightness(brightness);
                        System.out.println("device updated successfully");
                    }
                    catch (NumberFormatException e) {
                        throw new Exception("invalid value");
                    }
                }

                if (property.equals("temperature")) {
                    try {
                        int temperature = Integer.parseInt(value);
                        if (temperature < 10 || temperature > 30) {
                            throw new Exception("invalid value");
                        }
                        ((Thermostat) device).setTemperature(temperature);
                        System.out.println("device updated successfully");
                    }
                    catch (NumberFormatException e) {
                        throw new Exception("invalid value");
                    }
                }
            }
        }
    }

    public void removeDevice(String name) {
        for (SmartDevice device : devices) {
            if (device.getName().equals(name)) {
                devices.remove(device);
                System.out.println("device removed successfully");
            }
        }
        System.out.println("device not found");
    }

    public void listDevices() {
        if (devices.isEmpty()) {
            System.out.println();
        }

        for (SmartDevice device : devices) {
            System.out.println(device.getInfo());
        }
    }

    public void addRule(String deviceName , String time , String action)throws Exception{
        boolean notFound = true;
        for(SmartDevice device : devices){
            if(device.getName().equals(deviceName)){
                notFound = false;
                break;
            }
        }
        if(notFound){
            throw new Exception("device not found");
        }

        if (!time.matches("([01]\\d|2[0-3]):[0-5]\\d")){
            throw new Exception("invalid time");
        }

        if(!action.equals("on") && !action.equals("off")){
            throw new Exception("invalid action");
        }

        for(Rule rule : rules){
            if(rule.getDeviceName().equals(deviceName) && rule.getTime().equals(time)){
                throw new Exception("duplicate rule");
            }
        }

        rules.add(new Rule(deviceName , time , action));
        System.out.println("rule added successfully");
    }

    public void checkRules(String currentTime)throws Exception{
        if(!currentTime.matches("([01]\\d|2[0-3]):[0-5]\\d")){
            throw new Exception("invalid time");
        }

        for(Rule rule : rules){
            if(rule.getTime().equals(currentTime)){
                for(SmartDevice device : devices){
                    if(device.getName().equals(rule.getDeviceName())){
                        device.setStatus(rule.getAction());
                    }
                }
            }
        }

        System.out.println("rules checked");
    }

    public void listRules(){
        if(rules.isEmpty()){
            System.out.println();
        }

        for(Rule rule : rules){
            System.out.println(rule.getDeviceName() + " " + rule.getTime() + " " + rule.getAction());
        }
    }
}


