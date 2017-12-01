package sistemahotel.control.telaprincipal;

import java.awt.event.ActionEvent;
import java.util.List;

import com.sun.org.apache.regexp.internal.RE;
import sistemahotel.model.infraestrutura.RetornaListas;
import sistemahotel.model.pessoa.Cliente;

public class ControleRelatorios {
    List<Cliente> clientes = RetornaListas.listClientes();

    public void btImprimirActionHandler(ActionEvent event) {

    }

    public void btVoltarActionHandler(ActionEvent event) {
    }
}
