/**
 * Tramways API
 * Tramways API
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 *
 */

import ApiClient from '../ApiClient';
import UserRole from './UserRole';

/**
 * The UserAllOf model module.
 * @module model/UserAllOf
 * @version 1.0.0
 */
class UserAllOf {
    /**
     * Constructs a new <code>UserAllOf</code>.
     * @alias module:model/UserAllOf
     */
    constructor() { 
        
        UserAllOf.initialize(this);
    }

    /**
     * Initializes the fields of this object.
     * This method is used by the constructors of any subclasses, in order to implement multiple inheritance (mix-ins).
     * Only for internal use.
     */
    static initialize(obj) { 
    }

    /**
     * Constructs a <code>UserAllOf</code> from a plain JavaScript object, optionally creating a new instance.
     * Copies all relevant properties from <code>data</code> to <code>obj</code> if supplied or a new instance if not.
     * @param {Object} data The plain JavaScript object bearing properties of interest.
     * @param {module:model/UserAllOf} obj Optional instance to populate.
     * @return {module:model/UserAllOf} The populated <code>UserAllOf</code> instance.
     */
    static constructFromObject(data, obj) {
        if (data) {
            obj = obj || new UserAllOf();

            if (data.hasOwnProperty('username')) {
                obj['username'] = ApiClient.convertToType(data['username'], 'String');
            }
            if (data.hasOwnProperty('roles')) {
                obj['roles'] = ApiClient.convertToType(data['roles'], [UserRole]);
            }
        }
        return obj;
    }


}

/**
 * @member {String} username
 */
UserAllOf.prototype['username'] = undefined;

/**
 * @member {Array.<module:model/UserRole>} roles
 */
UserAllOf.prototype['roles'] = undefined;






export default UserAllOf;

