export interface ResponseEntity<D> {
  message : string;
  statusCode : number;
  statusName : string;
  data : D ;
}
