package de.arraying.arraybot.core.data.database.categories;

import de.arraying.arraybot.core.data.database.core.EntryField;
import de.arraying.arraybot.core.data.database.templates.HashEntry;
import de.arraying.arraybot.util.UDefaults;

import java.util.HashMap;

/**
 * Copyright 2017 Arraying
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class FilterBypassEntry extends HashEntry<FilterBypassEntry.Fields> {

    private final HashMap<Fields, EntryField> fields = new HashMap<>();

    /**
     * Creates a new announcement entry.
     */
    public FilterBypassEntry() {
        super(Category.FILTER_BYPASS);
        for(Fields field : Fields.values()) {
            fields.put(field, field.field);
        }
    }

    /**
     * Gets a field by key.
     * @param key The key.
     * @return A field.
     */
    @Override
    protected EntryField getField(Fields key) {
        return fields.get(key);
    }

    public enum Fields {

        /**
         * The bypass' per guild unique ID.
         */
        BYPASS_ID(new EntryField("bypass_id", UDefaults.DEFAULT_ID)),

        /**
         * The bypass type.
         */
        TYPE(new EntryField("type", UDefaults.DEFAULT_BYPASS_TYPE)),

        /**
         * The bypass value.
         */
        VALUE(new EntryField("value", UDefaults.DEFAULT_SNOWFLAKE));

        private final EntryField field;

        /**
         * Sets the entry field.
         * @param field The field.
         */
        Fields(EntryField field) {
            this.field = field;
        }

    }

}
