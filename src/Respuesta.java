import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class Respuesta {

    // Map directamente para 'conversion_rates'
    @SerializedName("conversion_rates")
    private Map<String, Double> conversionRates;

    public Map<String, Double> getConversionRates() {
        return conversionRates;
    }
}

