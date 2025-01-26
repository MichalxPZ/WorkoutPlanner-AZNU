
package put.poznan.pl.michalxpz.workoutplannersoap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WorkoutSOAPException complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WorkoutSOAPException"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="workoutId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WorkoutSOAPException", propOrder = {
    "workoutId",
    "message"
})
public class WorkoutSOAPException {

    protected Long workoutId;
    protected String message;

    /**
     * Gets the value of the workoutId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getWorkoutId() {
        return workoutId;
    }

    /**
     * Sets the value of the workoutId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setWorkoutId(Long value) {
        this.workoutId = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

}
