package put.poznan.pl.michalxpz.workoutintegration.model.workout;

/*
 * Travel mircro service
 * Micro service to book a travel
 *
 * OpenAPI spec version: 1.0.0
 * Contact: supportm@bp.org
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.processing.Generated;
/**
 * ExceptionResponse
 */

@Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-12-06T08:44:40.322365400+01:00[Europe/Warsaw]")
public class ExceptionResponse {
    @JsonProperty("timestamp")
    private String timestamp = null;

    @JsonProperty("message")
    private String message = null;

    public ExceptionResponse timestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    /**
     * Get timestamp
     * @return timestamp
     **/

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public ExceptionResponse message(String message) {
        this.message = message;
        return this;
    }

    /**
     * Get message
     * @return message
     **/

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExceptionResponse exceptionResponse = (ExceptionResponse) o;
        return Objects.equals(this.timestamp, exceptionResponse.timestamp) &&
                Objects.equals(this.message, exceptionResponse.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, message);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExceptionResponse {\n");

        sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
        sb.append("    message: ").append(toIndentedString(message)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

}
