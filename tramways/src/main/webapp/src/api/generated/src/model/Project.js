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
import Action from './Action';
import ProjectAllOf from './ProjectAllOf';
import Resource from './Resource';

/**
 * The Project model module.
 * @module model/Project
 * @version 1.0.0
 */
class Project {
    /**
     * Constructs a new <code>Project</code>.
     * @alias module:model/Project
     * @extends module:model/Resource
     * @implements module:model/Resource
     * @implements module:model/ProjectAllOf
     */
    constructor() { 
        Resource.initialize(this);ProjectAllOf.initialize(this);
        Project.initialize(this);
    }

    /**
     * Initializes the fields of this object.
     * This method is used by the constructors of any subclasses, in order to implement multiple inheritance (mix-ins).
     * Only for internal use.
     */
    static initialize(obj) { 
    }

    /**
     * Constructs a <code>Project</code> from a plain JavaScript object, optionally creating a new instance.
     * Copies all relevant properties from <code>data</code> to <code>obj</code> if supplied or a new instance if not.
     * @param {Object} data The plain JavaScript object bearing properties of interest.
     * @param {module:model/Project} obj Optional instance to populate.
     * @return {module:model/Project} The populated <code>Project</code> instance.
     */
    static constructFromObject(data, obj) {
        if (data) {
            obj = obj || new Project();
            Resource.constructFromObject(data, obj);
            Resource.constructFromObject(data, obj);
            ProjectAllOf.constructFromObject(data, obj);

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
Project.prototype['name'] = undefined;

/**
 * @member {String} ownerURI
 */
Project.prototype['ownerURI'] = undefined;


// Implement Resource interface:
/**
 * @member {Array.<module:model/Action>} actions
 */
Resource.prototype['actions'] = undefined;
// Implement ProjectAllOf interface:
/**
 * @member {String} name
 */
ProjectAllOf.prototype['name'] = undefined;
/**
 * @member {String} ownerURI
 */
ProjectAllOf.prototype['ownerURI'] = undefined;




export default Project;
