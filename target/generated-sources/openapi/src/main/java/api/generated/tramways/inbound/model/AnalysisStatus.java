package tramways.inbound.model;

import java.util.Objects;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonValue;
import javax.validation.constraints.*;
public enum AnalysisStatus {
    IN_PROGRESS, DONE, ERROR
}
