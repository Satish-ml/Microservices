package com.easybytes.accounts.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Schema(
        name = "Response",
        description = "Schema to hold successful response information"
)
public class ResponseDTO {

    @Schema(
            description = "Status code in the response"
    )
    private String statusCode;

    @Schema(
            description = "Status message in the response"
    )
    private String statusMesage;

   public ResponseDTO(String statusCode,String statusMessage){
       this.statusCode=statusCode;
       this.statusMesage=statusMessage;
   }
}
