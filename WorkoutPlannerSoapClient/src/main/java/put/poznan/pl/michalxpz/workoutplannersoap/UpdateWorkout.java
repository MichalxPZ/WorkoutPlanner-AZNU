
package put.poznan.pl.michalxpz.workoutplannersoap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for updateWorkout complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="updateWorkout"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="arg0" type="{http://workoutplannersoap.michalxpz.pl.poznan.put/}updateWorkoutSOAPRequest" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateWorkout", propOrder = {
    "arg0"
})
public class UpdateWorkout {

    protected UpdateWorkoutSOAPRequest arg0;

    /**
     * Gets the value of the arg0 property.
     * 
     * @return
     *     possible object is
     *     {@link UpdateWorkoutSOAPRequest }
     *     
     */
    public UpdateWorkoutSOAPRequest getArg0() {
        return arg0;
    }

    /**
     * Sets the value of the arg0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link UpdateWorkoutSOAPRequest }
     *     
     */
    public void setArg0(UpdateWorkoutSOAPRequest value) {
        this.arg0 = value;
    }

}
