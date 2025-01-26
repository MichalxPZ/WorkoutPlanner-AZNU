
package put.poznan.pl.michalxpz.workoutplannersoap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for addWorkout complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addWorkout"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="arg0" type="{http://workoutplannersoap.michalxpz.pl.poznan.put/}addWorkoutSOAPRequest" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addWorkout", propOrder = {
    "arg0"
})
public class AddWorkout {

    protected AddWorkoutSOAPRequest arg0;

    /**
     * Gets the value of the arg0 property.
     * 
     * @return
     *     possible object is
     *     {@link AddWorkoutSOAPRequest }
     *     
     */
    public AddWorkoutSOAPRequest getArg0() {
        return arg0;
    }

    /**
     * Sets the value of the arg0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddWorkoutSOAPRequest }
     *     
     */
    public void setArg0(AddWorkoutSOAPRequest value) {
        this.arg0 = value;
    }

}
