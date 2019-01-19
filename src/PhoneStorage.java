public class PhoneStorage {
    private class Node{
        private int value;
        private Node prev;
        private Node[] nodes=new Node[10];

        Node(){
            this.prev=null;
        }

        Node(int value){
            this.value=value;
            this.prev=null;
        }

        Node(int value, Node prev){
            this.value=value;
            this.prev=prev;
        }

        Node[] getNodes(){
            return nodes;
        }

        void setNodes(int index, Node node){
            nodes[index]=node;
        }

        void deleteNode(int index){
            nodes[index]=null;
        }

        void setPrev(Node prev){
            this.prev=prev;
        }

        Node getPrev(){
            return this.prev;
        }

        int getValue(){
            return this.value;
        }
    }
    private boolean found;
    private Node[] nodes=new Node[10];

    void addPhone(String phone) {
        if(nodes[phone.charAt(0)-'0'] == null){
            Node node=nodes[phone.charAt(0)-'0']=new Node(phone.charAt(0)-'0');
            add(1, phone, node);
        }
        else
            add(1, phone, nodes[phone.charAt(0)-'0']);
    }

    boolean findPhone(String phone){
        found=false;
        if(nodes[phone.charAt(0)-'0']==null)
            return false;
        else {
            find(1, phone, nodes[phone.charAt(0)-'0']);
        }
        return found;
    }

    boolean deletePhone(String phone){
        if(findPhone(phone)){
            Node lastPhoneNode=getLastNodeFromPhone(phone);
            while (true){
                if(lastPhoneNode.getPrev()==null) {
                    this.nodes[lastPhoneNode.getValue()]=null;
                    return true;
                }
                if(hasMore(lastPhoneNode.getPrev(), lastPhoneNode.getValue())){
                    lastPhoneNode.getPrev().getNodes()[lastPhoneNode.getValue()]=null;
                    return true;
                }
                else {
                    Node temp=lastPhoneNode.getPrev();
                    lastPhoneNode.getPrev().getNodes()[lastPhoneNode.getValue()]=null;
                    lastPhoneNode=temp;
                }
            }
        }
        return false;
    }

    private void add(int index, String phone, Node node){
        if(index >9)
            return;
        Node next=node.getNodes()[phone.charAt(index)-'0'];
        if(next==null){
            next=new Node(phone.charAt(index)-'0', node);
            node.getNodes()[phone.charAt(index)-'0']=next;
            add(++index, phone, next);
        }
        else {
            add(++index, phone, next);
        }

    }

    private void find(int index, String phone, Node node){
        if(index>9) {
            found = true;
            return;
        }
        Node next=node.getNodes()[phone.charAt(index)-'0'];
        if(index<9) {
            if (next == null)
                return;
            else
                find(++index, phone, next);
        }
        else{
            found=true;
        }
    }

    private Node getLastNodeFromPhone(String phone){
        return getLastNodeFromPhone(0, phone, nodes[phone.charAt(0)-'0']);
    }

    private Node getLastNodeFromPhone(int index, String phone, Node node){
        if(index==9){
            return node;
        }
        return getLastNodeFromPhone(++index, phone, node.getNodes()[phone.charAt(index)-'0']);
    }
    private boolean hasMore(Node node, int exclude){
        for(int i=0;i<node.getNodes().length;i++){
            if(i!=exclude) {
                if (node.getNodes()[i] != null)
                    return true;
            }
        }
        return false;
    }

}
