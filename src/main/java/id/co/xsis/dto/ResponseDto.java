package id.co.xsis.dto;

import id.co.xsis.helper.ResponseStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class ResponseDto {

    private int status;
    private String message;
    private List<MessageDto> messages;
    private List<?> data;
    private Long totalRecord;

    public ResponseDto(ResponseStatus status, String message, List<?> data, Long totalRecord, List<MessageDto> messages) {
        this.status = status.getValue();
        this.message = message;
        this.data = data;
        this.totalRecord = totalRecord;
        this.messages = messages;
    }

    public static ResponseDto ok() {
        return ok("SUCCESS");
    }

    public static ResponseDto ok(List<?> data) {
        return ok("SUCCESS", data);
    }

    public static ResponseDto ok(String message) {
        return ok(message, null);
    }

    public static ResponseDto ok(String message, List<?> data) {
        return ok(message, data, null, null);
    }

    public static ResponseDto ok(List<?> data, Long totalRecord) {
        return ok("SUCCESS", data, totalRecord, null);
    }

    public static ResponseDto ok(String message, List<?> data, Long totalRecord, List<MessageDto> messages) {
        return new ResponseDto(ResponseStatus.SUCCESS, message, data, totalRecord, messages);
    }

    public static ResponseDto err() {
        return err("FAILED");
    }

    public static ResponseDto err(String message) {
        return new ResponseDto(ResponseStatus.FAILED, message, null, null, null);
    }

    public static ResponseDto err(List<MessageDto> messages) {
        return new ResponseDto(ResponseStatus.FAILED, null, null, null, messages);
    }

}
