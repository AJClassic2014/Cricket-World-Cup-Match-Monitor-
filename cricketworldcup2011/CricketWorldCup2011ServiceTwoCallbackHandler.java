
/**
 * CricketWorldCup2011ServiceTwoCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.1  Built on : Oct 19, 2009 (10:59:00 EDT)
 */

    package cricketworldcup2011;

    /**
     *  CricketWorldCup2011ServiceTwoCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class CricketWorldCup2011ServiceTwoCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public CricketWorldCup2011ServiceTwoCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public CricketWorldCup2011ServiceTwoCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for getMatchIDs method
            * override this method for handling normal response from getMatchIDs operation
            */
           public void receiveResultgetMatchIDs(
                    cricketworldcup2011.CricketWorldCup2011ServiceTwoStub.GetMatchIDsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getMatchIDs operation
           */
            public void receiveErrorgetMatchIDs(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getBallFieldLabels method
            * override this method for handling normal response from getBallFieldLabels operation
            */
           public void receiveResultgetBallFieldLabels(
                    cricketworldcup2011.CricketWorldCup2011ServiceTwoStub.GetBallFieldLabelsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getBallFieldLabels operation
           */
            public void receiveErrorgetBallFieldLabels(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getLastBall method
            * override this method for handling normal response from getLastBall operation
            */
           public void receiveResultgetLastBall(
                    cricketworldcup2011.CricketWorldCup2011ServiceTwoStub.GetLastBallResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getLastBall operation
           */
            public void receiveErrorgetLastBall(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getMatchDetails method
            * override this method for handling normal response from getMatchDetails operation
            */
           public void receiveResultgetMatchDetails(
                    cricketworldcup2011.CricketWorldCup2011ServiceTwoStub.GetMatchDetailsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getMatchDetails operation
           */
            public void receiveErrorgetMatchDetails(java.lang.Exception e) {
            }
                


    }
    