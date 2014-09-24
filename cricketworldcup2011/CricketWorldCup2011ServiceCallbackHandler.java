
/**
 * CricketWorldCup2011ServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.1  Built on : Oct 19, 2009 (10:59:00 EDT)
 */

    package cricketworldcup2011;

    /**
     *  CricketWorldCup2011ServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class CricketWorldCup2011ServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public CricketWorldCup2011ServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public CricketWorldCup2011ServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for getMatchIDS method
            * override this method for handling normal response from getMatchIDS operation
            */
           public void receiveResultgetMatchIDS(
                    cricketworldcup2011.CricketWorldCup2011ServiceStub.GetMatchIDSResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getMatchIDS operation
           */
            public void receiveErrorgetMatchIDS(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getLastBall method
            * override this method for handling normal response from getLastBall operation
            */
           public void receiveResultgetLastBall(
                    cricketworldcup2011.CricketWorldCup2011ServiceStub.GetLastBallResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getLastBall operation
           */
            public void receiveErrorgetLastBall(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getBallFields method
            * override this method for handling normal response from getBallFields operation
            */
           public void receiveResultgetBallFields(
                    cricketworldcup2011.CricketWorldCup2011ServiceStub.GetBallFieldsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getBallFields operation
           */
            public void receiveErrorgetBallFields(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getMatchDetails method
            * override this method for handling normal response from getMatchDetails operation
            */
           public void receiveResultgetMatchDetails(
                    cricketworldcup2011.CricketWorldCup2011ServiceStub.GetMatchDetailsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getMatchDetails operation
           */
            public void receiveErrorgetMatchDetails(java.lang.Exception e) {
            }
                


    }
    