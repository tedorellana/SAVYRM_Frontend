package Controllers;

import DBAccess.PersonaDBA;
import RelationEntities.Persona_TipoPersona;

/**
 *
 * @author torellana
 */
public class PersonaController {
    
    public Persona_TipoPersona getPersona_PersonaTipoPorDNI(String numeroDocumentoPersona){
        Persona_TipoPersona persona_TipoPersona = new Persona_TipoPersona();
        
        PersonaDBA personaDBA = null;
        
        
        persona_TipoPersona = PersonaDBA.getPersona_PersonaTipoPorDNI(numeroDocumentoPersona);
        
        return persona_TipoPersona;
    }

    public String getNombrePersonaPorID(Integer idPersonaEmpleado) {
        String nombrePersona = "---";
        Persona_TipoPersona persona_TipoPersona = new Persona_TipoPersona();
        persona_TipoPersona = PersonaDBA.getNombrePersonaPorID(idPersonaEmpleado);
        nombrePersona = persona_TipoPersona.getApellidoPaternoPersona() + " " +
                persona_TipoPersona.getApellidoMaternoPersona() + " " +
                persona_TipoPersona.getNombrePersona();
        return nombrePersona;
    }
}
