package arewegreen.data;

import java.util.Objects;

public class ValueDto {

    public String value;

    ValueDto(String message) {
        this.value = message;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ValueDto valueDto = (ValueDto) o;
        return Objects.equals(value, valueDto.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
