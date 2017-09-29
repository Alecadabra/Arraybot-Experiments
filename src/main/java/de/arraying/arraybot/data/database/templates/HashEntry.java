package de.arraying.arraybot.data.database.templates;

import com.lambdaworks.redis.api.sync.RedisCommands;
import de.arraying.arraybot.data.database.Redis;
import de.arraying.arraybot.data.database.core.Entry;
import de.arraying.arraybot.data.database.core.EntryField;
import de.arraying.arraybot.util.UDatabase;

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
public abstract class HashEntry<T> implements Entry {

    private final Redis redis;
    private Category category;

    /**
     * Creates a new hash entry.
     */
    @SuppressWarnings("WeakerAccess")
    public HashEntry() {
        this.redis = Redis.getInstance();
    }

    /**
     * Gets a entry field via key.
     * @param key The key.
     * @return The entry field.
     */
    public abstract EntryField getField(T key);

    /**
     * Gets the parent category.
     * @return The parent category is a set containing all secondary keys.
     */
    public abstract Category getParent();

    /**
     * Gets the entry type.
     * @return The type.
     */
    @Override
    public Type getType() {
        return Type.HASH;
    }

    /**
     * Sets the category,
     * @param category The category.
     */
    @Override
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Deletes everything corresponding to the ID.
     * @param id The ID.
     */
    @Override
    public void delete(long id) {
        Category parentCategory = getParent();
        if(parentCategory == null) {
            return;
        }
        RedisCommands resource = redis.getResource();
        SetEntry parent = (SetEntry) parentCategory.getEntry();
        for(String key : parent.values(id)) {
            resource.del(UDatabase.getKey(category, id, key));
        }
        parent.delete(id);
    }

    /**
     * Gets a hash value.
     * @param field The field.
     * @param id The primary key.
     * @param secondaryKey The secondary key.
     * @return The value. It is never null.
     */
    public String fetch(EntryField field, long id, Object secondaryKey) {
        RedisCommands resource = redis.getResource();
        Object redisResult = resource.hget(UDatabase.getKey(category, id, secondaryKey), field.getRedisKey());
        return redisResult == null ? setDefault(field, id, secondaryKey) : redisResult.toString();
    }

    /**
     * Sets a hash value.
     * @param field The field.
     * @param id The primary key.
     * @param secondaryKey The secondary key.
     * @param value The value.
     */

    public void push(EntryField field, long id, Object secondaryKey, Object value) {
        RedisCommands resource = redis.getResource();
        resource.hset(UDatabase.getKey(category, id, secondaryKey), field.getRedisKey(), value.toString());
    }

    /**
     * Checks whether or not a field exists.
     * @param field The field.
     * @param id The primary key.
     * @param secondaryKey The secondary key.
     * @return The value.
     */
    public boolean exists(EntryField field, long id, Object secondaryKey) {
        RedisCommands resource = redis.getResource();
        return resource.hexists(UDatabase.getKey(category, id, secondaryKey), field.getRedisKey());
    }

    /**
     * Sets a field to its default value.
     * @param field The field.
     * @param id The primary key.
     * @param secondaryKey The secondary key.
     * @return The value.
     */
    private String setDefault(EntryField field, long id, Object secondaryKey) {
        if(field == null) {
            throw new IllegalArgumentException("The provided field is null.");
        }
        push(field, id, secondaryKey, field.getDefaultValue());
        return field.getDefaultValue().toString();
    }

}
