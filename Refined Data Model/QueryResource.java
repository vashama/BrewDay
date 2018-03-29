import com.google.gson.Gson;
import jdbc.Query;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryResource extends ServerResource {

    @Get
    public String represent() {
        return "Post your SQL queries to this endpoint";
    }

    @Post
    public Representation processQuery(Representation entity) throws IOException, JSONException {
        JSONObject json = new JsonRepresentation(entity).getJsonObject();

        if(!json.has("sql")) {
            return errorRepresentation("SQL parameter not supplied");
        }

        List<JSONObject> list = Query.execute(json.getString("sql"), resultSet -> {
            ArrayList<JSONObject> objList = new ArrayList<JSONObject>();
            ResultSetMetaData metaData = resultSet.getMetaData();
            while (resultSet.next()) {
                JSONObject jsonObject = jsonForRow(resultSet, metaData);
                objList.add(jsonObject);
            }

            return objList;
        });

        JSONArray array = new JSONArray(list);
        return new JsonRepresentation(array);
    }
    
}
