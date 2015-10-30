/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablas;


public class PeriodoSemestre {
    private String idperido;
    private String periodo;

    public PeriodoSemestre(String idperido) {
        this.idperido = idperido;
    }
    
    public String getIdperido() {
        return idperido;
    }

    public void setIdperido(String idperido) {
        this.idperido = idperido;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

   
    
}
