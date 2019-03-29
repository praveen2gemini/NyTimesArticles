package com.nytimes.apibase.gson;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.nytimes.apibase.logger.Logger;
import com.nytimes.apibase.services.models.Results;

import java.lang.reflect.Type;


public class ResultTypeConverter implements JsonDeserializer<Results> {

    private static final String KEY_DES_FACET = "des_facet";
    private static final String KEY_ORG_FACET = "org_facet";
    private static final String KEY_PER_FACET = "per_facet";
    private static final String KEY_GEO_FACET = "geo_facet";

    @Override
    public Results deserialize(JsonElement json, Type typeOfT,
                               JsonDeserializationContext context)
            throws JsonParseException {
        try {
            if (json.isJsonObject()) {
                JsonObject singleResultJson = json.getAsJsonObject();
                if (singleResultJson.has(KEY_DES_FACET)
                        && !(singleResultJson.get(KEY_DES_FACET).isJsonArray())) {
                    singleResultJson.add(KEY_DES_FACET, new JsonArray());
                }
                if (singleResultJson.has(KEY_ORG_FACET)
                        && !(singleResultJson.get(KEY_ORG_FACET).isJsonArray())) {
                    singleResultJson.add(KEY_ORG_FACET, new JsonArray());
                }
                if (singleResultJson.has(KEY_PER_FACET)
                        && !(singleResultJson.get(KEY_PER_FACET).isJsonArray())) {
                    singleResultJson.add(KEY_PER_FACET, new JsonArray());
                }
                if (singleResultJson.has(KEY_GEO_FACET)
                        && (singleResultJson.get(KEY_GEO_FACET).isJsonPrimitive())) {
                    singleResultJson.add(KEY_GEO_FACET, new JsonArray());
                }
                return new Gson().fromJson(singleResultJson, Results.class);
            } else {
                return null;
            }
        } catch (Exception e) {
            Logger.e("@@@@@", "Exception occurred on ResultTypeConverter", e);
            return null;
        }
    }
}
