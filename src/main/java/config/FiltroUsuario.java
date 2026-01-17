package config;

import java.util.Map;
import java.util.function.Function;

public class FiltroUsuario {
    //TIPO DE RESTRICCION
    private static final Map<RestrictionType, Function<Campo,String>> RESOLVERS
          = Map.of(
                 RestrictionType.BOOLEAN,Campo::value,
                 RestrictionType.CURRENT_USER,campo->SecurityContext.getCurrentUser()
     );
    //VALOR DEL CAMPO.
    public static final Function<Campo,String> valorCampo =
            campo->RESOLVERS.get(campo.restrictionType()).apply(campo);
    //PREDICADO.
    public static final Function<Map.Entry<String,Filtro>,String> construirFiltro =
            entry -> {
                Filtro filtro = entry.getValue();
                Campo campo = filtro.campo();
                return filtro.alias() + "." + campo.nombre() + "=" + valorCampo.apply(campo);
            };
}
