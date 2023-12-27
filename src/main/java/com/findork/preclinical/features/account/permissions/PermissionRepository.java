package com.findork.preclinical.features.account.permissions;

import com.findork.preclinical.features.account.users_permissions.UserPermissionProjection;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PermissionRepository extends MongoRepository<Permission, String> {
    Optional<Permission> findByName(String name);

    @Aggregation(pipeline = {
            "{$lookup: { 'from': 'users_permissions', 'localField': '_id', 'foreignField': 'permission_id', 'as': 'users_permissions'}}",
            "{$unwind: { 'path':'$users_permissions', 'preserveNullAndEmptyArrays': true}}",
            "{$match: { $or: [ { 'users_permissions.user_id': ?0 }, { 'users_permissions':{$eq:null} } ]}}",
            "{$project: { '_id': 0, 'permission_id': '$_id', permissionName: '$name', 'user_id': ?0, 'permissionType': '$users_permissions.permissionType'}}"
    })
    List<UserPermissionProjection> findAllPermissionsByUser(ObjectId userId);
}
