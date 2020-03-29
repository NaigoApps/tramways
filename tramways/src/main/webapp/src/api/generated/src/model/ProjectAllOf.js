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

/**
 * The ProjectAllOf model module.
 * @module model/ProjectAllOf
 * @version 1.0.0
 */
class ProjectAllOf {
    /**
     * Constructs a new <code>ProjectAllOf</code>.
     * @alias module:model/ProjectAllOf
     */
    constructor() { 
        
        ProjectAllOf.initialize(this);
    }

    /**
     * Initializes the fields of this object.
     * This method is used by the constructors of any subclasses, in order to implement multiple inheritance (mix-ins).
     * Only for internal use.
     */
    static initialize(obj) { 
    }

    /**
     * Constructs a <code>ProjectAllOf</code> from a plain JavaScript object, optionally creating a new instance.
     * Copies all relevant properties from <code>data</code> to <code>obj</code> if supplied or a new instance if not.
     * @param {Object} data The plain JavaScript object bearing properties of interest.
     * @param {module:model/ProjectAllOf} obj Optional instance to populate.
     * @return {module:model/ProjectAllOf} The populated <code>ProjectAllOf</code> instance.
     */
    static constructFromObject(data, obj) {
        if (data) {
            obj = obj || new ProjectAllOf();

            if (data.hasOwnProperty('name')) {
                obj['name'] = ApiClient.convertToType(data['name'], 'String');
            }
            if (data.hasOwnProperty('ownerURI')) {
                obj['ownerURI'] = ApiClient.convertToType(data['ownerURI'], 'String');
            }
        }
        return obj;
    }


}

/**
 * @member {String} name
 */
ProjectAllOf.prototype['name'] = undefined;

/**
 * @member {String} ownerURI
 */
ProjectAllOf.prototype['ownerURI'] = undefined;






export default ProjectAllOf;

