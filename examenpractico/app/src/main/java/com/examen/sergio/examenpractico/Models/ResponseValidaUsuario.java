
package com.examen.sergio.examenpractico.Models;

// se generó con http://www.jsonschema2pojo.org
public class ResponseValidaUsuario {

    private Dto dto;
    private Error error;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ResponseValidaUsuario() {
    }

    /**
     * 
     * @param dto
     * @param error
     */
    public ResponseValidaUsuario(Dto dto, Error error) {
        super();
        this.dto = dto;
        this.error = error;
    }

    public Dto getDto() {
        return dto;
    }

    public void setDto(Dto dto) {
        this.dto = dto;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

}
