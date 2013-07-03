package es.deusto.deustotech.androjena;

import java.util.HashMap;

public interface ICapability {
	
	static enum CAPABILITY {
		//USER & DEVICE
		BRIGHTNESS, VIEW_SIZE,
		OUTPUT,
		
		//CONTEXT EXCLUSIVE
        ILLUMINANCE, TEMPERATURE,
	};
	
	//User
	//TODO: What is big? Use Trends?
	static enum BRIGHTNESS {
		DEFAULT, LOW, HIGH, VERY_HIGH, 
		ONLY_LOW, ONLY_HIGH, ONLY_VERY_HIGH
	};
	
	static enum VIEW_SIZE {
		SMALL, 
		DEFAULT, 
		BIG, 
		VERY_BIG,
		ONLY_VERY_BIG
	}
	
    //If user cannot see he/she should have an option to
    //avoid HAPTIC -> ONLY_VOICE_CONTROL.
    static enum OUTPUT {
        ONLY_TEXT, DEFAULT, //text and images and audio
        ONLY_AUDIO
    };

    //Context
	static enum ILLUMINANCE {
		// extracted from http://en.wikipedia.org/wiki/Lux
		NIGHT, SUNLIGHT
	}
	
	static enum NOISE {
		//extracted from http://es.wikipedia.org/wiki/Decibelio
		NOISY, 
		NOT_NOISY	// 0 <= x < 50 dB
	};
	
	public String getCapabilityValue(final CAPABILITY capabilityName);
	public void setCapabilityValue(final CAPABILITY capabilityName, final Object value);
	public HashMap<CAPABILITY, Object> getAllCapabilities();
}

