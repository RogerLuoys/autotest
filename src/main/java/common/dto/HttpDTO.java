package common.dto;

import lombok.Data;

/**
 * http请求入参
 *
 * @author luoys
 */
@Data
public class HttpDTO {

    private String type;
    private String url;
    private String header;
    private String body;

}
