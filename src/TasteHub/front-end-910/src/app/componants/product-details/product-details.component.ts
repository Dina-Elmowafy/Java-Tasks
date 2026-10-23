import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpClient} from '@angular/common/http';

interface ProductDetails {
  id?: number;
  productId: number;
  details: string;
  ingredients: string;
}

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {
  model: ProductDetails = {productId: 0, details: '', ingredients: ''};
  message = '';
  error = '';
  loading = false;

  private readonly baseUrl = 'http://localhost:6060/product-details';

  constructor(private route: ActivatedRoute, private http: HttpClient) {}

  ngOnInit(): void {
    this.model.productId = Number(this.route.snapshot.paramMap.get('productId'));
    this.load();
  }

  load(): void {
    this.loading = true;
    this.http.get<ProductDetails>(`${this.baseUrl}/by-product/${this.model.productId}`).subscribe(
      value => { this.model = value; this.loading = false; },
      () => { this.model.id = undefined; this.loading = false; }
    );
  }

  save(): void {
    this.message = '';
    this.error = '';
    const request = this.model.id
      ? this.http.put<ProductDetails>(`${this.baseUrl}/${this.model.id}`, this.model)
      : this.http.post<ProductDetails>(this.baseUrl, this.model);
    request.subscribe(
      value => { this.model = value; this.message = 'تم حفظ التفاصيل بنجاح / Details saved successfully'; },
      response => { this.error = this.readMessage(response); }
    );
  }

  delete(): void {
    if (!this.model.id) { return; }
    this.http.delete(`${this.baseUrl}/${this.model.id}`).subscribe(
      () => {
        this.model = {productId: this.model.productId, details: '', ingredients: ''};
        this.message = 'تم حذف التفاصيل / Details deleted';
        this.error = '';
      },
      response => { this.error = this.readMessage(response); }
    );
  }

  private readMessage(response: any): string {
    const bundle = response && response.error && response.error.bundleMessage;
    return bundle ? `${bundle.message_ar || ''} ${bundle.message_en || ''}`.trim() : 'تعذر إتمام الطلب / Request failed';
  }
}
