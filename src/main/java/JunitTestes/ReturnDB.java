package JunitTestes;

import org.hibernate.Session;
import sistemahotel.model.local.Habitacao;
import sistemahotel.model.infraestrutura.Persistencia;

public class ReturnDB {
    Persistencia persistencia = Persistencia.getInstancia();
    Session session = persistencia.getSsf().openSession();

    private Habitacao entity;
    public Habitacao getById(Long id) {

        return (Habitacao) session.get(entity.getClass(), id);
    }

}
