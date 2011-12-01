package rbhide0.graph;

/**
 * @author Ravi Bhide
 */
public class DefaultVertex implements Vertex {
    private Object _label;
    private Object _satelliteData;

    public DefaultVertex(Object label) {
        this(label, null);
    }

    public DefaultVertex(Object label, Object satelliteData) {
        _label = label;
        _satelliteData = satelliteData;
    }

    public Object getLabel() {
        return _label;
    }

    public Object getSatelliteData() {
        return this;
    }

    public boolean equals(Object v) {
        boolean equals = false;
        if (v instanceof DefaultVertex) {
            if (v != null) {
                if (_label == null) {
                    equals = (((DefaultVertex) v)._label == null);
                } else {
                    equals = _label.equals(((DefaultVertex) v)._label);
                }
            }
        }
        return equals;
    }

    public int hashCode() {
        return _label == null ? 0 : _label.hashCode();
    }

    public String toString() {
        return getLabel().toString();
    }
}
