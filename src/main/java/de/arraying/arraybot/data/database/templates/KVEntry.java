package de.arraying.arraybot.data.database.templates;

import de.arraying.arraybot.data.database.Redis;
import de.arraying.arraybot.data.database.core.Category;
import de.arraying.arraybot.data.database.core.Entry;
import de.arraying.arraybot.util.UDatabase;
import io.lettuce.core.api.sync.RedisCommands;

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
@SuppressWarnings("unchecked")
public abstract class KVEntry implements Entry {

    private final Redis redis;
    protected Category category;

    /**
     * Creates a new key/value entry.
     */
    protected KVEntry() {
        this.redis = Redis.INSTANCE;
    }


    /**
     * Gets the entry type.
     * @return The type.
     */
    @Override
    public final Type getType() {
        return Type.KV;
    }

    /**
     * Does nothing.
     * @param id The ID.
     */
    @SuppressWarnings("unused")
    @Override
    public final void deleteGuild(long id) {
    }

    /**
     * Gets a value from Redis.
     * @param key The key.
     * @return The value, can be null.
     */
    public final String get(String key) {
        RedisCommands resource = redis.getResource();
        Object result = resource.get(UDatabase.getKey(category, key));
        return result == null ? null : result.toString();
    }

    /**
     * Sets a value in Redis.
     * @param key The key.
     * @param value The value. Cannot be null.
     */
    public final void set(String key, Object value) {
        RedisCommands resource = redis.getResource();
        resource.set(UDatabase.getKey(category, key), value.toString());
    }

}
