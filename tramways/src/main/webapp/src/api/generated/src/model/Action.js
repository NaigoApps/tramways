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
import ActionMethod from './ActionMethod';

/**
 * The Action model module.
 * @module model/Action
 * @version 1.0.0
 */
class Action {
    /**
     * Constructs a new <code>Action</code>.
     * @alias module:model/Action
     */
    constructor() { 
        
        Action.initialize(this);
    }

    /**
     * Initializes the fields of this object.
     * This method is used by the constructors of any subclasses, in order to implement multiple inheritance (mix-ins).
     * Only for internal use.
     */
    static initialize(obj) { 
    }

    /**
     * Constructs a <code>Action</code> from a plain JavaScript object, optionally creating a new instance.
     * Copies all relevant properties from <code>data</code> to <code>obj</code> if supplied or a new instance if not.
     * @param {Object} data The plain JavaScript object bearing properties of interest.
     * @param {module:model/Action} obj Optional instance to populate.
     * @return {module:model/Action} The populated <code>Action</code> instance.
     */
    static constructFromObject(data, obj) {
        if (data) {
            obj = obj || new Action();

            if (data.hasOwnProperty('name')) {
                obj['name'] = ApiClient.convertToType(data['name'], 'String');
            }
            if (data.hasOwnProperty('description')) {
                obj['description'] = ApiClient.convertToType(data['description'], 'String');
            }
            if (data.hasOwnProperty('uri')) {
                obj['uri'] = ApiClient.convertToType(data['uri'], 'String');
            }
            if (data.hasOwnProperty('method')) {
                obj['method'] = ActionMethod.constructFromObject(data['method']);
            }
        }
        return obj;
    }


}

/**
 * @member {String} name
 */
Action.prototype['name'] = undefined;

/**
 * @member {String} description
 */
Action.prototype['description'] = undefined;

/**
 * @member {String} uri
 */
Action.prototype['uri'] = undefined;

/**
 * @member {module:model/ActionMethod} method
 */
Action.prototype['method'] = undefined;






export default Action;
