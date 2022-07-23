package DAOmysql;

import DAOinterface.PlanDAO;
import modelo.academico.plan.Plan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MySQLPlan implements PlanDAO {

    //querys
    final String INSERT = "INSERT INTO plan (anio, estado) VALUES (?, ?)";
    final String UPDATE = "UPDATE plan SET estado = ? WHERE anio = ?";
    final String UPDATED = "UPDATE plan SET estado = ? WHERE  anio = ?";
    final String GETPLANCOMPLETE = "SELECT * FROM plan INNER JOIN anioplan ON plan.anio = anioplan.plan_aniofk AND INNER JOIN materia ON anioplan.anioplan_plan = materia.anioplan AND plan.anio = materia.plan_anio WHERE plan.anio = ?";
    final String GETALL = "SELECT * FROM plan INNER JOIN anioplan ON plan.anio = anioplan.plan_aniofk AND INNER JOIN materia ON anioplan.anioplan_plan = materia.anioplan AND plan.anio = materia.plan_anio";


    private Connection connection;

    public MySQLPlan(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void guardar(Plan p) {
        PreparedStatement stat = null;
        try {
            stat = connection.prepareStatement(INSERT);
            stat.setInt(1, p.getAnio());
            stat.setString(2, p.estadoString());
            stat.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    public void eliminar(Plan p) {
        PreparedStatement stat = null;
        try {
            stat = connection.prepareStatement(UPDATED);
            stat.setInt(2, p.getAnio());
            p.setEstadoBorrador();
            stat.setString(1, p.estadoString() );
            stat.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void actualizar(Plan p){
        PreparedStatement stat = null;
        try{
            stat = connection.prepareStatement(UPDATED);
            stat.setString(1, p.estadoString());
            stat.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Plan buscar(Plan p) {
        PreparedStatement stat = null;
        try{
            stat = connection.prepareStatement(GETPLANCOMPLETE);
            stat.setInt(1, p.getAnio());
            stat.executeQuery();
            return p;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Plan> buscarTodos() {
        PreparedStatement stat = null;
        try {
            stat = connection.prepareStatement(GETALL);
            stat.executeQuery();
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

