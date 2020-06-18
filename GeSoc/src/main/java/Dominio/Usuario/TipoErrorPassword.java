package Dominio.Usuario;

public enum TipoErrorPassword {
    SECUENCIAL {
        public String getError() {
            return "La contraseña no debe tener secuencias: EJ: aaa,111";
        }
    },
    TOP10000 {
        public String getError() {
            return "La contraseña es muy insegura";
        }
    },
    LONGITUD {
        public String getError() {
            return "La contraseña debe poseer un mínimo de 8 caracteres";
        }
    },
    MOCK {
        public String getError() {
            return "La contraseña es invalida";
        }
    };

    public abstract String getError();
}
