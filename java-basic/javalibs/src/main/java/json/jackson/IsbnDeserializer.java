package json.jackson;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.math.BigInteger;

/**
 * Created by Defias on 2020/08.
 * Description: 为BigInteger类型的字段自定义反序列化器

 供Book中@JsonDeserialize使用
 */
public class IsbnDeserializer extends JsonDeserializer<BigInteger> {

    @Override
    public BigInteger deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        String s = p.getValueAsString();
        if (s != null) {
            try {
                return new BigInteger(s.replace("-", ""));
            } catch (NumberFormatException e) {
                throw new JsonParseException(p, s, e);
            }
        }
        return null;
    }
}