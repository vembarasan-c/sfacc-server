package edu.lmsService.api.util;

import java.lang.reflect.Field;

public class MapperUtil {

    /**
     * Converts source object (DTO) to target object (DAO).
     * Assumes matching field names and compatible field types.
     *
     * @param source      The source object to map from.
     * @param targetClass The target class type to map to.
     * @param <T>         The type of the target class.
     * @return The mapped target object.
     */
    public static <T> T map(Object source, Class<T> targetClass) {
        try {
            T target = targetClass.getDeclaredConstructor().newInstance();
            Field[] sourceFields = source.getClass().getDeclaredFields();

            for (Field sourceField : sourceFields) {
                sourceField.setAccessible(true);
                Field targetField;
                try {
                    targetField = targetClass.getDeclaredField(sourceField.getName());
                    targetField.setAccessible(true);

                    if (sourceField.getType().equals(targetField.getType())) {
                        targetField.set(target, sourceField.get(source));
                    }
                } catch (NoSuchFieldException e) {
                    // Field does not exist in target, ignore and continue
                }
            }
            return target;
        } catch (Exception e) {
            throw new RuntimeException("Mapping failed", e);
        }
    }

    /**
     * Copy fields from source to target, skipping null values.
     *
     * @param source The source object to copy from.
     * @param target The target object to copy to.
     */
    public static void copyNonNullFields(Object source, Object target) {
        Field[] fields = source.getClass().getDeclaredFields();

        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(source);

                if (value != null) {
                    Field targetField = target.getClass().getDeclaredField(field.getName());
                    targetField.setAccessible(true);
                    targetField.set(target, value);
                }
            } catch (Exception e) {
                // Field not found or inaccessible in the target, skip it
                System.err.println("Error copying field: " + field.getName() + " - " + e.getMessage());
            }
        }
    }
}